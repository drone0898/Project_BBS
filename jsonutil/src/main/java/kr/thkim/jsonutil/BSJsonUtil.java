package kr.thkim.jsonutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import kr.thkim.jsonutil.from.FromDTO;
import kr.thkim.jsonutil.from.FromLocationDTO;
import kr.thkim.jsonutil.from.ItemDTO;
import kr.thkim.jsonutil.to.AcqDTO;
import kr.thkim.jsonutil.to.AnimalDTO;
import kr.thkim.jsonutil.to.CombDTO;
import kr.thkim.jsonutil.to.DropDTO;
import kr.thkim.jsonutil.to.DropItemInfo;
import kr.thkim.jsonutil.to.EffectDTO;
import kr.thkim.jsonutil.to.EffectTypeDTO;
import kr.thkim.jsonutil.to.GenAnimalInfo;
import kr.thkim.jsonutil.to.ToDTO;
import kr.thkim.jsonutil.to.ToItemDTO;
import kr.thkim.jsonutil.to.ToLocationDTO;

// https://docs.google.com/spreadsheets/d/1sFB5_d6E_qT5USTLQQ9_V4JVMmv0tmo-PIPDSdLa83A/edit#gid=1775545479
// 시트 내용중 item_info 시트만 json으로 바꿔서 from.json에 붙여넣은 후 main 실행...

// 엑셀데이터 -> db 정규화 및 이름기준 관계를 ID기준 관계로 변경하는 과정실행...
public class BSJsonUtil {

    public static void main(String[] args) {
        //        System.out.println("Working Directory = " + System.getProperty("user.dir"));
//        Working Directory = C:\Users\drone\AndroidStudioProjects\Project_BBS
        FromDTO dto = ParseUtil.fromJson(ParseUtil.readJsonFile(
                "./jsonutil/assets/from.json"), FromDTO.class);

        ToDTO to = new ToDTO();
        List<ToItemDTO> toItems = new ArrayList<>();
        List<ToLocationDTO> toLocations = new ArrayList<>();
        List<AnimalDTO> animals = new ArrayList<>();
        List<EffectTypeDTO> effectTypes = new ArrayList<>();
        to.setItems(toItems);
        to.setLocations(toLocations);
        to.setAnimals(animals);
        to.setEffectTypes(effectTypes);


        dto.getLocations().forEach(loc->{
            ToLocationDTO toLoc = new ToLocationDTO();
            toLoc.setId(loc.getId());
            toLoc.setName(loc.getName());
            toLoc.setEngName(loc.getEngName());
            toLocations.add(toLoc);
        });

        dto.getEffectTypes().forEach(ets->{
            EffectTypeDTO efft = new EffectTypeDTO();
            efft.setId(ets.getId());
            efft.setDesc(ets.getDesc());
            efft.setEngDesc(ets.getEngDesc());
            efft.setName(ets.getName());
            efft.setEngName(ets.getEngName());
            efft.setValType(ets.getValType());
            effectTypes.add(efft);
        });

        AtomicInteger animalIndex = new AtomicInteger(0);
        dto.getItems().forEach(item -> {
                    try {
                        if (item.getKinds().equals("야생동물")) {
                            AnimalDTO animal = new AnimalDTO();
                            animal.setAnimalId(animalIndex.getAndIncrement());
                            animal.setAnimalName(item.getName());
                            animal.setAnimalEngName(item.getEngName());
                            animals.add(animal);
                            setLocationGenAnimal(item.getAcqLoc().split(","),animal,toLocations);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println(" : " + item.toString());
                    }
                }
        );

        dto.getItems().forEach(item -> {
            try {
                if (!item.getKinds().equals("야생동물")) {
                    ToItemDTO toItem = new ToItemDTO(item);
                    addEffects(item.getEffect().split(","), toItem.getEffect(), dto.getEffectTypes());
                    addacqLoc(item.getAcqLoc().split(","), toItem.getAcqLoc(), dto.getLocations());
                    addCombMat(item.getCombMat().split(","), toItem.getCombMat(), dto.getItems());
                    addDrops(item.getDrop().split(","),toItem.getDrop(),animals);
                    toItems.add(toItem);
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(" : " + item.toString());
            }
        });

        toItems.forEach(toItem->{
            if(toItem.getAcqLoc().size()>0){
                toItem.getAcqLoc().forEach(loc->{
                    DropItemInfo dropInfo = new DropItemInfo();
                    dropInfo.setItemId(toItem.getId());
                    dropInfo.setAmount(loc.getAmount());
                    toLocations.get(loc.getLocationId()).getDropItems().add(dropInfo);
                });
            }
            if(toItem.getDrop().size()>0){
                toItem.getDrop().forEach(drop->{
                    animals.get(drop.getAnimalId()).getDropItems().add(toItem.getId());
                });
            }
        });

        ParseUtil.writeToJsonFile(to, "./jsonutil/assets/to.json");
    }

    public static void addEffects(String[] effects, List<EffectDTO> effectList,
                                  List<EffectTypeDTO> effectTypes) {
        if (effects == null || effects.length == 0) {
            return;
        }
        Arrays.stream(effects).forEach(effect -> {
            String[] div = effect.split("_");
            if (div != null && div.length == 2) {
                String effectName = div[0];
                String value = div[1];
                EffectTypeDTO type = effectTypes.stream().filter(types -> effectName.equals(types.getName()))
                        .findFirst().orElse(null);
                int id = type == null ? -1 : type.getId();
                effectList.add(new EffectDTO(id, value));
            }
        });
    }

    public static void addacqLoc(String[] acqs, List<AcqDTO> acqList,
                                 List<FromLocationDTO> locations) {
        if (acqs == null || acqs.length == 0) {
            return;
        }
        Arrays.stream(acqs).forEach(acq -> {
            String[] div = acq.split("_");
            if (div != null && div.length == 2) {
                String locName = div[0];
                int amount = 0;
                amount = Integer.parseInt(div[1]);
                FromLocationDTO l = locations.stream().filter(loc -> locName.equals(loc.getName()))
                        .findFirst().orElse(null);
                int id = l == null ? -1 : l.getId();
                acqList.add(new AcqDTO(id, amount));
            }
        });
    }

    public static void setLocationGenAnimal(String[] acqs, AnimalDTO animal, List<ToLocationDTO> locList) {
        if (acqs == null || acqs.length == 0) {
            return;
        }
        Arrays.stream(acqs).forEach(acq -> {
            String[] div = acq.split("_");
            if (div != null && div.length == 2) {
                String locName = div[0];
                int amount = 0;
                amount = Integer.parseInt(div[1]);
                ToLocationDTO loc = locList.stream().filter(tloc -> tloc.getName().equals(locName)).findFirst().orElse(null);
                if(loc!=null){
                    loc.getGenAnimals().add(new GenAnimalInfo(animal.getAnimalId(),amount));
                }
            }
        });
    }

    public static void addCombMat(String[] comb, List<CombDTO> combList, List<ItemDTO> itemList) {
        if (comb == null || comb.length == 0) {
            return;
        }
        Arrays.stream(comb).forEach(combms -> {
            String[] div = combms.split("_");
            if (div != null && div.length == 2) {
                ItemDTO item = itemList.stream().filter(itm -> itm.getName().equals(div[0])).findFirst().orElse(null);
                int id = item == null ? -1 : item.getId();
                int req = 1;
                req = Integer.parseInt(div[1]);
                combList.add(new CombDTO(id, req));
            }
        });
    }

    public static void addDrops(String[] drops, List<DropDTO> dropList, List<AnimalDTO> animalList) {
        if (drops == null || drops.length == 0) {
            return;
        }
        Arrays.stream(drops).forEach(d -> {
            String[] div = d.split("_");
            if (div != null && div.length == 2) {
                float chance = 0;
                AnimalDTO animal = animalList.stream().filter(anml -> anml.getAnimalName().equals(div[0])).findFirst().orElse(null);
                int animalId = animal == null ? -1 : animal.getAnimalId();
                chance = Float.parseFloat(div[1]);
                dropList.add(new DropDTO(animalId, chance));
            }
        });
    }
}
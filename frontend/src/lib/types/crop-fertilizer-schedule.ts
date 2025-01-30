import {CropSchedule} from "@/lib/types/crop-schedule";
import {Fertilizer} from "@/lib/types/fertilizer";
import {Period} from "@/lib/types/period";
import {FertilizerApplicationMethod} from "@/lib/enums/fertilizer-application-method";

export interface CropFertilizerSchedule {
    id: number,
    cropSchedule: CropSchedule,
    fertilizer: Fertilizer,
    stageOfGrowth: Period,
    applicationInterval: Period,
    rate: number,
    applicationMethod: FertilizerApplicationMethod,
    remarks: string
}
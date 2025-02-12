// import DashboardLayout from "@/layouts/DashboardLayout";
// import React from "react";
// import {ChevronRight, Mail} from "lucide-react";
// import {Avatar, AvatarFallback, AvatarImage} from "@/components/ui/avatar";
// import {Badge} from "@/components/ui/badge";
// import PatientBasicInfo from "@/components/cards/patient-basic-info";
// import AppointmentSchedule from "@/components/cards/appointment-schedule-card";
// import {PatientMetricCard} from "@/components/cards/patient-metric-card";
// import {AreaChartMetric} from "@/components/charts/patient-area";
// import {BMIIndicator} from "@/components/cards/bmi-indicator";
// import {MedicalHistory} from "@/components/cards/medical-history";
// import {UpcomingSchedules} from "@/components/cards/upcoming-schedule";
//
// function PatientDashboard() {
//     return (
//         <DashboardLayout>
//             <div className="flex w-screen p-5 space-y-6 min-h-screen flex-col ">
//                 <header className="flex items-center gap-2 py-4">
//                     <h1 className="text-2xl font-bold text-[#14532d]">Dashboard</h1>
//                     <ChevronRight className="h-5 w-5 text-muted-foreground"/>
//                     <span className="text-muted-foreground text-[#a1a1aa]">Patient Dashboard</span>
//                 </header>
//
//                 <div className="flex items-start justify-between">
//                     <div className="flex items-center gap-4">
//                         <Avatar className="h-16 w-16">
//                             <AvatarImage src="/placeholder.svg" alt="Profile picture"/>
//                             <AvatarFallback>JB</AvatarFallback>
//                         </Avatar>
//                         <div>
//                             <div className="flex items-center gap-2">
//                                 <h1 className="text-2xl font-bold">Jerome Bellingham</h1>
//                                 <Badge variant="secondary" className="bg-green-100 text-green-800">
//                                     MEMBER
//                                 </Badge>
//                             </div>
//                             <p className="text-muted-foreground">Joined Since : 12 March 2023</p>
//                         </div>
//                     </div>
//                     <button className="rounded-full p-2 hover:bg-accent">
//                         <Mail className="h-5 w-5"/>
//                     </button>
//                 </div>
//                 <div className=" grid gap-6 md:grid-cols-2 lg:grid-cols-2">
//                     <PatientBasicInfo policyMember={policy}/>
//                     <AppointmentSchedule/>
//                 </div>
//                 <div className="container mx-auto p-6 space-y-6">
//                     <div className="grid gap-6 md:grid-cols-2 lg:grid-cols-4">
//                         <PatientMetricCard
//                             title="Heart Rate"
//                             value={110}
//                             unit="bpm"
//                             change={12}
//                             type="primary"
//                             icon="heart"
//                         />
//                         <PatientMetricCard
//                             title="Heart Rate"
//                             value={38.6}
//                             unit="°C"
//                             change={-20}
//                             type="danger"
//                             icon="skull"
//                         />
//                         <PatientMetricCard
//                             title="Heart Rate"
//                             value={120}
//                             unit="mm/Hg"
//                             change={-40}
//                             type="warning"
//                             icon="thumbs"
//                         />
//                         <PatientMetricCard
//                             title="Heart Rate"
//                             value="7h 30m"
//                             unit=""
//                             change={-10}
//                             type="success"
//                             icon="clock"
//                         />
//                     </div>
//
//                     <div className="grid gap-6 md:grid-cols-2">
//                         <AreaChartMetric/>
//                         <BMIIndicator/>
//                     </div>
//
//                     <div className="grid gap-6 md:grid-cols-2">
//                         <MedicalHistory/>
//                         <UpcomingSchedules/>
//                     </div>
//                 </div>
//             </div>
//         </DashboardLayout>
//
//     )
//
// }
//
// export default PatientDashboard
//
//

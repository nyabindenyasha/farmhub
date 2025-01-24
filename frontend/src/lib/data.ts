import {
    CalendarDateRangeIcon,
    ChartBarIcon,
    ClipboardDocumentListIcon,
    HomeIcon,
    UserGroupIcon
} from "@heroicons/react/24/outline";
import {TimerIcon} from "@radix-ui/react-icons";
import {Patient as PatientType} from '@/lib/types'

export const nav_items = [

    {name: 'Home',location:'/',id:'1'},
    {name: 'About',location:'/about',id:'2'},
    {name: 'Contact',location:'/contact',id:'3'},
];

export const dashboard_nav_items ={
    sideBar: [
        {name:'Dashboard',location:'/dashboard',id:'1',Icon: HomeIcon,active: true},
        {name:'Appointment',location:'/dashboard/appointment',id:'2',Icon: CalendarDateRangeIcon},
        {name:'Patient',location:'/dashboard/patient',id:'3',Icon: ClipboardDocumentListIcon},
        {name:'Doctor Schedule',location:'/dashboard/doctor_schedule',id:'4',Icon: TimerIcon},
        {name:'Reports',location:'/dashboard/report',id:'5',Icon: ChartBarIcon},
        // {name:'PatientDashboard',location:'/dashboard/patientDashboard',id:'3',Icon: ClipboardDocumentListIcon},


    ],
    navBar:[
        {name:'Home',location:'/dashboard',id:'1',Icon: UserGroupIcon},
        {name:'Profile',location:'dashboard/profile',id:'2',Icon: UserGroupIcon}
    ]

}


export const patients: PatientType[] = [
    {
        id: "1",
        name: "Theresa Webb",
        avatar: "/placeholder.svg",
        caseRef: "CC/80564",
        openedAt: "22/10/2022",
        doa: "22/10/2022",
        source: "Google",
        serviceProvider: "CC/DGM",
        services: [
            {name: "S&R", variant: "secondary"},
            {name: "Hire", variant: "success"},
            {name: "VD", variant: "default"},
        ],
        amount: 230.0,
    },
    {
        id: "2",
        name: "Wade Warren",
        avatar: "/placeholder.svg",
        caseRef: "CC/80564",
        openedAt: "22/10/2022",
        doa: "22/10/2022",
        source: "LinkedIn",
        serviceProvider: "CC/DGM",
        services: [
            {name: "S&R", variant: "secondary"},
            {name: "Hire", variant: "success"},
            {name: "VD", variant: "default"},
        ],
        amount: 230.0,
    },
]
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
        {name:'Crops',location:'/dashboard/crop',id:'2',Icon: ClipboardDocumentListIcon},
        {name:'Fertilizer',location:'/dashboard/fertilizer',id:'3',Icon: CalendarDateRangeIcon},
        {name:'Pesticides',location:'/dashboard/pesticide',id:'4',Icon: ClipboardDocumentListIcon},
        {name:'Crop Program',location:'/dashboard/cropprogram',id:'5',Icon: TimerIcon},
        {name:'Crop Stages',location:'/dashboard/cropstages',id:'6',Icon: TimerIcon},
        {name:'Crop Variety',location:'/dashboard/cropvariety',id:'7',Icon: TimerIcon},
        {name:'Reports',location:'/dashboard/report',id:'6',Icon: ChartBarIcon},
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
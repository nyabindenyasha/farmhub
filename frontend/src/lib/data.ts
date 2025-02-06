import {
    CalendarDateRangeIcon,
    ChartBarIcon,
    ClipboardDocumentListIcon,
    HomeIcon,
    UserGroupIcon
} from "@heroicons/react/24/outline";
import {TimerIcon} from "@radix-ui/react-icons";
import {Patient as PatientType} from '@/lib/types'
import {NavItem} from "@/lib/types/nav-item";
import {Role} from "@/lib/enums/role";
import {ShieldCheck} from "lucide-react";

export const nav_items = [

    {name: 'Home', location: '/', id: '1'},
    {name: 'About', location: '/about', id: '2'},
    {name: 'Contact', location: '/contact', id: '3'},
];

export const dashboard_nav_items = {
    sideBar: [
        {name: 'Dashboard', location: '/dashboard', id: '1', Icon: HomeIcon, active: true},
        {name: 'Crops', location: '/dashboard/crop', id: '2', Icon: ClipboardDocumentListIcon},
        {name: 'Fertilizer', location: '/dashboard/fertilizer', id: '3', Icon: CalendarDateRangeIcon},
        {name: 'Pesticides', location: '/dashboard/pesticide', id: '4', Icon: ClipboardDocumentListIcon},
        {name: 'Crop Program', location: '/dashboard/cropprogram', id: '5', Icon: TimerIcon},
        {name: 'Crop Stages', location: '/dashboard/cropstages', id: '6', Icon: TimerIcon},
        {name: 'Crop Variety', location: '/dashboard/cropvariety', id: '7', Icon: TimerIcon},
        {name: 'Reports', location: '/dashboard/report', id: '6', Icon: ChartBarIcon},
        // {name:'PatientDashboard',location:'/dashboard/patientDashboard',id:'3',Icon: ClipboardDocumentListIcon},


    ],
    navBar: [
        {name: 'Home', location: '/dashboard', id: '1', Icon: UserGroupIcon},
        {name: 'Profile', location: 'dashboard/profile', id: '2', Icon: UserGroupIcon}
    ]

}

export const sideBarDashboardNavItems: NavItem[] = [
    {
        name: "Dashboard",
        location: "/dashboard",
        id: "1",
        icon: HomeIcon,
        active: true,
        roles: [Role.ADMIN,
            Role.FARMER]
    },
    {
        name: "Crops",
        location: "/dashboard/crop",
        id: "2",
        icon: ClipboardDocumentListIcon,
        roles: [Role.ADMIN],
    },
    {
        name: "Fertilizer",
        location: "/dashboard/fertilizer",
        id: "3",
        icon: CalendarDateRangeIcon,
        roles: [Role.ADMIN]
    },
    {
        name: "Pesticides",
        location: "/dashboard/pesticide",
        id: "4",
        icon: ClipboardDocumentListIcon,
        roles: [Role.ADMIN],
    },
    {
        name: "Crop Program",
        location: "/dashboard/cropprogram",
        id: "5",
        icon: TimerIcon,
        roles: [Role.ADMIN]
    },
    {
        name: "Crop Stages",
        location: "/dashboard/cropstages",
        id: "6",
        icon: TimerIcon,
        roles: [Role.ADMIN]
    },
    {
        name: "Crop Variety",
        location: "/dashboard/cropvariety",
        id: "7",
        icon: TimerIcon,
        roles: [Role.ADMIN]
    },
    {
        name: "Reports",
        location: "/dashboard/report",
        id: "8",
        icon: ChartBarIcon,
        roles: [Role.ADMIN]
    },



    {
        name: "Crop Programs",
        location: "/farmerdashboard/cropprogram",
        id: "9",
        icon: ShieldCheck,
        roles: [Role.FARMER]
    },
    {
        name: "Crop Batches",
        location: "/farmerdashboard/cropbatch",
        id: "10",
        icon: TimerIcon,
        roles: [Role.FARMER]
    },
];


export const navBarDashboardNavItems: NavItem[] = [
    {name: "Home", location: "/dashboard", id: "1", icon: UserGroupIcon, roles: [Role.ADMIN, Role.FARMER]},
    {name: "Profile", location: "dashboard/profile", id: "2", icon: UserGroupIcon, roles: [Role.ADMIN, Role.FARMER]},
];


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
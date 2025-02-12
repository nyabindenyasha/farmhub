"use client";

import { Button as PrimeButton } from "primereact/button";
import { classNames } from "primereact/utils";

export type ButtonVariant =
    | "default"
    | "destructive"
    | "outline"
    | "secondary"
    | "ghost"
    | "link";

export type ButtonSize = "default" | "sm" | "lg" | "icon";

export interface ButtonProps {
    label: string;
    variant?: ButtonVariant;
    size?: ButtonSize;
    icon?: React.ReactNode;
    className?: string;
    onClick?: () => void;
}

const buttonStyles = {
    default: "bg-primary text-primary-foreground shadow hover:bg-primary/90",
    destructive: "bg-red-500 text-white shadow-sm hover:bg-red-600",
    outline: "border border-gray-300 bg-white text-black hover:bg-gray-100",
    secondary: "bg-gray-200 text-black shadow-sm hover:bg-gray-300",
    ghost: "bg-transparent hover:bg-gray-100",
    link: "text-blue-500 underline-offset-4 hover:underline",
};

const sizeStyles = {
    default: "h-9 px-4 py-2 text-sm",
    sm: "h-8 px-3 text-xs",
    lg: "h-10 px-8",
    icon: "h-9 w-9 flex items-center justify-center",
};

export default function Button({
                                   label,
                                   variant = "default",
                                   size = "default",
                                   icon,
                                   className,
                                   onClick,
                               }: ButtonProps) {
    return (
        <PrimeButton
            label={label}
            className={classNames(buttonStyles[variant], sizeStyles[size], className)}
            icon={icon}
            onClick={onClick}
        />
    );
}
"use client"

import Link from "next/link"
import { Sprout } from "lucide-react"
import { Button } from "@/components/ui/button"
import { useEffect, useState } from "react"
import {useRouter} from "next/navigation";

export function SiteHeader() {
    const router = useRouter();
    const [scrolled, setScrolled] = useState(false)

    useEffect(() => {
        const handleScroll = () => {
            setScrolled(window.scrollY > 50)
        }

        window.addEventListener("scroll", handleScroll)
        return () => window.removeEventListener("scroll", handleScroll)
    }, [])

    return (
        <header
            className={`fixed top-0 z-50 w-full transition-colors duration-300 ${
                scrolled ? "bg-white shadow-md" : "bg-transparent"
            }`}
        >
            <div className="container flex h-20 items-center justify-between">
                <Link href="/" className="flex items-center space-x-2">
                    <Sprout className={`h-8 w-8 ${scrolled ? "text-[#2F5A41]" : "text-[#2ECC71]"}`} />
                    <span className={`text-2xl font-bold ${scrolled ? "text-[#2F5A41]" : "text-white"}`}>FarmHub</span>
                </Link>
                <nav className="hidden md:flex items-center space-x-6">
                    {[
                        ["Home", "/"],
                        ["About Us", "/about"],
                        ["Service", "/service"],
                        ["Projects", "/projects"],
                        ["Blog", "/blog"],
                        ["Pages", "/pages"],
                        ["Contact", "/contact"],
                    ].map(([label, href]) => (
                        <Link
                            key={label}
                            href={href}
                            className={`text-sm font-medium transition-colors ${
                                scrolled ? "text-gray-600 hover:text-gray-900" : "text-white hover:text-white/80"
                            }`}
                        >
                            {label}
                        </Link>
                    ))}
                    <Button onClick={() => router.push("/login")} variant="secondary" className="bg-[#2ECC71] text-white hover:bg-[#2ECC71]/90">
                        Login →
                    </Button>
                </nav>
            </div>
        </header>
    )
}


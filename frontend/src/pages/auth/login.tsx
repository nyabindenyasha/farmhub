import { useState } from "react"
import { AnimatePresence } from "framer-motion"
import HeroSection from "@/pages/auth/hero-section";
import {AuthForm} from "@/pages/auth/auth-form";

export default function Login() {
    const [showSignUp, setShowSignUp] = useState(false)

    const toggleSignUp = () => {
        setShowSignUp(!showSignUp)
    }

    return (
        <div className="flex min-h-screen">
            <AnimatePresence initial={false} mode="wait">
                {!showSignUp ? (
                    <div key="hero" className="hidden lg:flex lg:w-1/2">
                        <HeroSection />
                    </div>
                ) : (
                    <div key="signup" className="flex w-full lg:w-1/2 items-center justify-center p-8">
                        <AuthForm initialMode="signup" />
                    </div>
                )}
            </AnimatePresence>
            <div className="flex w-full lg:w-1/2 items-center justify-center p-8">
                {!showSignUp ? (
                    <AuthForm initialMode="signin" />
                ) : (
                    <HeroSection />
                )}
            </div>
            <button
                onClick={toggleSignUp}
                className="fixed bottom-4 right-4 bg-primary text-primary-foreground px-4 py-2 rounded-md"
            >
                {showSignUp ? "Back to Sign In" : "Sign Up"}
            </button>
        </div>
    )
}


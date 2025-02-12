import Image from "next/image"
import {LoginForm} from "@/othercomponents/login/login";


export default function LoginPage() {
    return (
        <main className="flex min-h-screen flex-col lg:flex-row">
            {/* Left side - Image */}
            <div className="relative hidden lg:block lg:w-1/2">
                <Image
                    src="/images/woman-in-vegetable.jpg"
                    alt="Xplug Events Banner"
                    fill
                    className="object-cover"
                    priority
                />
                <div className="absolute inset-0 bg-black/50" />
                <div className="absolute top-8 inset-x-0 z-10 flex justify-center">
                    <h1 className="text-4xl font-bold text-white">Farm Hub</h1>
                </div>
            </div>

            {/* Right side - Login Form */}
            <div className="flex w-full items-center justify-center p-4 lg:w-1/2 lg:p-8">
                <div className="w-full max-w-md space-y-8">
                    <LoginForm />
                </div>
            </div>
        </main>
    )
}

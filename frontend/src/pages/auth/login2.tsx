// import React, {useState} from "react";
// import Image from "next/image";
// import PrimaryInput from "@/components/inputs/customInput";
// import PrimaryButton from "@/components/buttons/customButton";
//
//
// type Props = {}
//
// function Login({}: Props) {
//
//     const [name, setName] = useState()
//     const [password, setPassword] = useState()
//     const [loading, setLoading] = useState(false)
//     const handleLogin = async () => {
//         setLoading(true)
//         try {
// // TODO: - handle mlogin ``
//             setLoading(false)
//         } catch (e) {
//             setLoading(false)
//         }
//     }
//     return (
//         <div className={"flex justify-center bg-zinc-50 w-screen h-screen  p-4"}>
//             <div
//                 className={"flex-1  flex relative items-center content-center justify-center rounded-3xl overflow-hidden "}>
//                 <Image src={"/istock.jpg"} fill={true} alt={"logo"}/>
//             </div>
//             <div className={"flex-1 flex flex-col  justify-center bg-zinc-50   border-zinc-300/50"}>
//                 <div className={"flex flex-col   space-y-6  rounded-md max-w-sm w-full mx-auto  "}>
//
//                     <div className={'w-full flex flex-row items-center space-x-1'}>
//                         <div className={"relative h-10 w-20"}>
//                             <Image src={"/logo-bonvie.png"} fill={true} alt={"logo"}/>
//                         </div>
//                         <p className={"text-sm text-zinc-950 font-medium"}>Managed Care </p>
//                     </div>
//                     <div>
//                         <p className={"text-xl font-bold text-zinc-950"}>Login into your account</p>
//                         <p className={"text-sm text-zinc-400"}>Welcome back!!</p>
//                     </div>
//                     <PrimaryInput placeholder={"email@gmail.com"} value={name} setValue={setName} label={"Email"}/>
//                     <PrimaryInput placeholder={"*****"} value={password} setValue={setPassword} label={"Password"}
//                                   type={"password"}/>
//
//                     <p className={"text-zinc-400 text-xs text-right"}>Forget Password</p>
//
//                     <PrimaryButton text={"Login"} onClick={handleLogin} secondary/>
//                     <div className={"flex flex-row space-x-4 mx-auto "}>
//                         <p className={"text-zinc-400 text-xs"}>Dont have an account?<span
//                             className={"text-zinc-950 font-semibold"}> Create an account</span></p>
//                     </div>
//                 </div>
//             </div>
//         </div>
//
//
//     )
//
// }
//
// export default Login
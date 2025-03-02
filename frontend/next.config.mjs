/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: true,
  transpilePackages: ['three'],
  output: "export", // 👈 Enable static export
  distDir: ".next", // (Optional) Change build output folder
};

export default nextConfig;

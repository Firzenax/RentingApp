import { Outlet, Route, Routes } from "react-router-dom";
import "./App.css";
import LandingPage from "./Pages/LandingPage";
import RentingPage from "./Pages/RentingPage";
import Navbar from "./components/Navbar/Navbar";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";

const queryClient = new QueryClient();

function App() {
  return (
    <div>
      <QueryClientProvider client={queryClient}>
        <Routes>
          <Route path="/" element={<Layout />}>
            <Route index element={<LandingPage />} />
            <Route path="rent" element={<RentingPage />} />
          </Route>
        </Routes>
      </QueryClientProvider>
    </div>
  );
}

const Layout = () => {
  return (
    <>
      <Navbar />
      <div className=" py-8">
        <Outlet />
      </div>
    </>
  );
};

export default App;

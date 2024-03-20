import { RouterProvider, createBrowserRouter } from "react-router-dom";
import "./App.css";
import LandingPage from "./Pages/LandingPage";
import RentingPage from "./Pages/RentingPage";
import Navbar from "./components/Navbar/Navbar";

function App() {
  const router = createBrowserRouter([
    {
      path: "/home",
      element: <LandingPage />,
    },
    {
      path: "/rent",
      element: <RentingPage />,
    },
  ]);

  return (
    <>
      <Navbar />
      <RouterProvider router={router} />
    </>
  );
}

export default App;

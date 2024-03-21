import { Link } from "react-router-dom";
import Car from "../components/svg/Car";

const LandingPage = () => {
  return (
    <>
      <div
        id="container"
        className="grid gap-4 grid-cols-2 grid-rows-2 w-auto "
      >
        <div id="Panel" className=" row-span-2 ">
          <img
            className="rounded-sm"
            src="https://images.unsplash.com/photo-1549399542-7e3f8b79c341?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
          />
        </div>
        <div
          id="Description"
          className=" col-span-1 bg-slate-400 justify-center flex items-center"
        >
          <h1 className=" content-center">
            Welcome to Catch a Car, rent your dream car to go for your dream
            roadtrip
          </h1>
        </div>
        <Link
          to="/rent"
          id="Renting"
          className=" col-span-1 bg-slate-800 rounded-lg justify-center flex items-center"
        >
          <Car />
        </Link>
      </div>
    </>
  );
};

export default LandingPage;

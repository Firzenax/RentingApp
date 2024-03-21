import { useEffect, useState } from "react";
import useCompanies from "../Hooks/useCompanies";
import Company from "../Types/Comapny";
import User from "../Types/User";
import axios from "axios";

const RentingPage = (user: User) => {
  const { status, data, error } = useCompanies();

  const [companies, setCompanies] = useState<Company[]>([]);

  const rentCar = async (company_vehicle_id: number) => {
    const axiosInstance = axios.create({
      baseURL: "http://localhost:8080",
    });
    await axiosInstance.post(
      `/rents/user/${user.user_id}?company_vehicle=${company_vehicle_id}`
    );
  };

  const showDates = () => {};

  useEffect(() => {
    setCompanies(data);
    console.log(data);
  }, [data]);

  if (status === "pending") {
    return <h1>Loading</h1>;
  }

  if (error) return <p>An error occured {error.message}</p>;

  return (
    <div className=" grid gap-4 grid-cols-1 grid-rows-10 w-auto">
      {companies?.map((company: Company) => (
        <div key={crypto.randomUUID()} className=" border-2">
          <span className=" text-xl">{company?.name}</span>
          {company.companyVehicles?.map((vehicle) => (
            <div>
              <p key={crypto.randomUUID()}>
                Vehicle No : {vehicle.companyVehicle_id}{" "}
                {vehicle.vehicle.vehicleName}
              </p>
              {user ? (
                <div>
                  <input type="date" name="startRentDate" id="startRentDate" />
                  <input type="date" name="endRentDate" id="endRentDate" />
                  <button>Rent</button>
                </div>
              ) : null}
            </div>
          ))}
        </div>
      ))}
    </div>
  );
};

export default RentingPage;

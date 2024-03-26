import { useEffect, useState } from "react";
import useCompanies from "../Hooks/useCompanies";
import Company from "../Types/Comapny";
import User from "../Types/User";
import axios from "axios";
import CompanyVehicleList from "../components/Cards/CompanyVehicleList";

const RentingPage = (user?: User) => {
  const { status, data, error } = useCompanies();

  const [companies, setCompanies] = useState<Company[]>([]);

  useEffect(() => {
    setCompanies(data);
    console.log(user);
  }, [data]);

  if (status === "pending") {
    return <h1>Loading</h1>;
  }

  if (error) return <p>An error occured {error.message}</p>;

  return (
    <div className=" grid gap-4 grid-cols-1  w-auto ">
      {companies?.map((company: Company) => (
        <div key={crypto.randomUUID()} className=" border-2">
          <span className=" text-xl">{company?.name}</span>
          <div className="grid grid-cols-2 h-auto">
            {company.companyVehicles?.map((companyVehicle) => (
              <CompanyVehicleList
                key={crypto.randomUUID()}
                companyVehicle_id={companyVehicle.companyVehicle_id}
                brand={companyVehicle.brand}
                vehicle={companyVehicle.vehicle}
              />
            ))}
          </div>
        </div>
      ))}
    </div>
  );
};

export default RentingPage;

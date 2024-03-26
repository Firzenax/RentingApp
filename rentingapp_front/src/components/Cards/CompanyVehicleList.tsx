import { FC, useEffect, useState } from "react";
import CompanyVehicle from "../../Types/CompanyVehicle";
import User from "../../Types/User";
import axios from "axios";

const CompanyVehicleList = (companyVehicle: CompanyVehicle, user: User) => {
  const [startRentDate, setstartRentDate] = useState<Date>();
  const [endRentDate, setendRentDate] = useState<Date>();
  const [error, seterror] = useState<string>();

  const toDateParam = (date: Date | undefined) => {
    var dateParam: string = "";
    if (date!.getDate() < 10) {
      dateParam = "0" + date?.getDate().toString() + "-";
    } else {
      dateParam = date?.getDate().toString() + "-";
    }
    if (date!.getMonth() < 10) {
      dateParam +=
        "0" +
        (date!.getMonth() + 1).toString() +
        "-" +
        date?.getFullYear().toString();
    } else {
      dateParam +=
        date?.getMonth().toString() + "-" + date?.getFullYear().toString();
    }
    return dateParam;
  };

  const onSubmit = async (company_vehicle_id: number | undefined) => {
    if (!startRentDate || !endRentDate) {
      seterror("Please specify the renting dates");
    } else {
      var startDate: string = toDateParam(startRentDate);
      var endDate: string = toDateParam(endRentDate);

      const axiosInstance = axios.create({
        baseURL: "http://localhost:8080",
      });
      await axiosInstance.post(
        `/rents/user/1?company_vehicle_id=${company_vehicle_id}`,
        {
          rentStartDateStr: startDate,
          rentEndDateStr: endDate,
        }
      );
    }
  };

  useEffect(() => {
    setTimeout(() => {
      seterror(undefined);
    }, 5000);
    setstartRentDate(undefined);
  }, [error]);

  return (
    <div className=" p-8">
      <p key={crypto.randomUUID()}>
        Vehicle No : {companyVehicle?.companyVehicle_id}{" "}
        {companyVehicle?.vehicle.brand.brandName}{" "}
        {companyVehicle?.vehicle.vehicleName}
      </p>
      {user ? (
        <div>
          <input
            type="date"
            name="startRentDate"
            id="startRentDate"
            onChange={(e) => setstartRentDate(new Date(e.target.value))}
          />
          <input
            type="date"
            name="endRentDate"
            id="endRentDate"
            onChange={(e) => setendRentDate(new Date(e.target.value))}
          />
          <button onClick={() => onSubmit(companyVehicle?.companyVehicle_id)}>
            Rent
          </button>
        </div>
      ) : null}
      {error ? <p className=" text-red-600">{error}</p> : null}
    </div>
  );
};

export default CompanyVehicleList;

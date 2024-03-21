import CompanyVehicle from "./CompanyVehicle";

type Rent = {
  rent_id: number;
  rentStartDate: Date;
  rentEndDate: Date;
  companyVehicle: CompanyVehicle;
};

export default Rent;

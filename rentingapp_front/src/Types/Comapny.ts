import CompanyVehicle from "./CompanyVehicle";

type Company = {
  company_id: number;
  name: string;
  companyVehicles: CompanyVehicle[];
};

export default Company;

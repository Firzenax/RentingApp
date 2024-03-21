import Brand from "./Brand";
import Vehicle from "./Vehicle";

type CompanyVehicle = {
  companyVehicle_id: number;
  vehicle: Vehicle;
  brand: Brand;
};

export default CompanyVehicle;

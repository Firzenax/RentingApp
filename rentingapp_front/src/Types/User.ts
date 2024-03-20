type User = {
  user_id: number;
  firstName: string;
  lastName: string;
  email: string;
  rent: Rent[];
};

type Rent = {
  rent_id: number;
  rentStartDate: Date;
  rentEndDate: Date;
  companyVehicle: CompanyVehicle;
};

type CompanyVehicle = {
  companyVehicle_id: number;
  vehicleName: string;
  brand: Brand;
};

type Vehicle = {
  vehicle_id: number;
  vehicleName: string;
  brand: Brand;
};

type Brand = {
  id: number;
  brandName: string;
};

type Company = {
  company_id: number;
  name: string;
  companyVehicles: CompanyVehicle[];
};

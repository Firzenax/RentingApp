import axios from "axios";
import { useQuery } from "@tanstack/react-query";

const useCompanies = () => {
  const axiosInstance = axios.create({
    baseURL: "http://localhost:8080",
  });
  return useQuery({
    queryKey: ["todos"],
    queryFn: async () => {
      const { data } = await axiosInstance.get("/companies");
      return data;
    },
  });
};

export default useCompanies;

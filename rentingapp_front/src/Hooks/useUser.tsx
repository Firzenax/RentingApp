import axios from "axios";
import { useQuery } from "@tanstack/react-query";

const useUsers = (email: string | undefined) => {
  const axiosInstance = axios.create({
    baseURL: "http://localhost:8080",
  });
  return useQuery({
    queryKey: ["todos"],
    queryFn: async () => {
      const { data } = await axiosInstance.get("/users/login", {
        params: {
          email: email,
        },
      });
      return data;
    },
  });
};

export default useUsers;

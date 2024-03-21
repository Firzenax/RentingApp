import Rent from "./Rent";

type User = {
  user_id: number;
  firstName: string;
  lastName: string;
  email: string;
  rent: Rent[];
};

export default User;

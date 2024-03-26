import { Dispatch, FormEventHandler, SetStateAction, useState } from "react";
import User from "../Types/User";
import useUsers from "../Hooks/useUser";

const signInPage = (setUser: Dispatch<SetStateAction<User>>) => {
  const [userName, setUserName] = useState<string>("");
  const [error, setError] = useState<string>();

  const Login = (): any => {
    try {
      const { data } = useUsers(userName);
      setUser(data);
      return data;
    } catch (e: any) {
      setError(e);
    }
  };

  return (
    <>
      <form onSubmit={Login()}>
        <input type="text" />
        <input type="submit" />
      </form>
      {error ? <h1>{error}</h1> : null}
    </>
  );
};

export default signInPage;

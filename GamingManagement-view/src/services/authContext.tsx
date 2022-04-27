import * as React from "react";

export enum Role {
  Player = "player",
  Admin = "admin",
}

type Context = {
  role: null | Role;
  onSetRole: (role: Role | null) => void;
};

const contextDefault = {
  role: null,
  onSetRole: () => {},
};

const context = React.createContext<Context>(contextDefault);

const { Provider } = context;

type Props = {
  children: React.ReactNode;
};

export const AuthProvider = ({ children }: Props) => {
  const [role, setRole] = React.useState<Role | null>(null);

  return <Provider value={{ role, onSetRole: setRole }}>{children}</Provider>;
};

export const useAuth = () => React.useContext(context);

export const useRole = () => {
  const { role } = useAuth();

  return role;
};

export const useIsAdmin = () => {
  const role = useRole();

  return role === Role.Admin;
};

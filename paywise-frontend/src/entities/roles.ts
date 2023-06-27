import { User } from "./user";

export interface Roles {
  id: number;
  user_role: User; // Add the User type
  role: string;
}

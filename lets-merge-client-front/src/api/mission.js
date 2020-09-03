import { API_BASE_URL } from "@/utils/constants";
import axios from "axios";

const client = axios.create({
  //TODO: users/{userId} 삭제
  baseURL: API_BASE_URL.EC2 + "/api/v1/users/{userId}/missions",
});

const api = (() => {
  const loadMissions = () => client.get("/1/missions").then((res) => res.data);
  return {
    loadMissions,
  };
})();

export default api;
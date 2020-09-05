import { API_BASE_URL } from "@/utils/constants";
import axios from "axios";

const client = axios.create({
  //TODO: users/{userId} 삭제
  baseURL: API_BASE_URL.EC2 + "/api/v1/users/1/missions",
});

const api = (() => {
  const loadMissions = () =>
    client.get("").then((res) => res.data.data.missionWithStatusResponse);
  return {
    loadMissions,
  };
})();

export default api;

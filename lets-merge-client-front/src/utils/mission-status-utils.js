import { MISSION_STATUS } from "@/utils/constants";

const findMissionStatusBy = (missionStatus) => {
  return MISSION_STATUS.find((mission) => mission.key === missionStatus);
};

export { findMissionStatusBy };

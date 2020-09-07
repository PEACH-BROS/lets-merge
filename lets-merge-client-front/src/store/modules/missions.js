import dayjs from "dayjs";
import api from "@/api/mission";
import { MISSION_STATUS } from "@/utils/constants";

export default {
  namespaced: true,
  state: {
    assignedMissions: [],
    openedMissions: [],
    closedMissions: [],
    missions: [],
  },
  mutations: {
    CHANGE_DATE_TIME_FORMAT(state) {
      state.missions.forEach((mission) => {
        mission.startDateTime = dayjs(mission.startDateTime).format(
          "YYYY.MM.DD HH:mm",
        );
        mission.dueDateTime = dayjs(mission.dueDateTime).format(
          "YYYY.MM.DD HH:mm",
        );
      });
    },
    SET_ASSIGNED_MISSIONS(state) {
      state.assignedMissions = state.missions.filter(
        (mission) =>
          mission.status === MISSION_STATUS.ASSIGNED ||
          mission.status === MISSION_STATUS.SHOW_RESULT,
      );
    },
    SET_OPENED_MISSIONS(state) {
      state.openedMissions = state.missions.filter(
        (mission) => mission.status === MISSION_STATUS.ASSIGNABLE,
      );
    },
    SET_CLOSED_MISSIONS(state) {
      state.closedMissions = state.missions.filter(
        (mission) => mission.status === MISSION_STATUS.CLOSED,
      );
    },
    ASSIGN_MISSION(state, missionId) {
      const targetMissionIndex = state.openedMissions.findIndex(mission => mission.id === missionId);
      const targetMission = state.openedMissions.splice(targetMissionIndex, 1)[0];
      targetMission.status = MISSION_STATUS.ASSIGNED;
      state.assignedMissions.push(targetMission);
    },
    CANCEL_MISSION(state, missionId) {
      const targetMissionIndex = state.assignedMissions.findIndex(mission => mission.id === missionId);
      const targetMission = state.assignedMissions.splice(targetMissionIndex, 1)[0];
      targetMission.status = MISSION_STATUS.ASSIGNABLE;
      state.openedMissions.push(targetMission);
    },
  },
  actions: {
    async setMissions({ commit, state }) {
      state.missions = await api.loadMissions();
      commit("CHANGE_DATE_TIME_FORMAT");
      commit("SET_ASSIGNED_MISSIONS");
      commit("SET_OPENED_MISSIONS");
      commit("SET_CLOSED_MISSIONS");
    },
  },
  getters: {
    getAssignedMissions: (state) => {
      return state.assignedMissions;
    },
    getOpenedMissions: (state) => {
      return state.openedMissions;
    },
    getClosedMissions: (state) => {
      return state.closedMissions;
    },
    getMissions: (state) => {
      return state.missions;
    },
  },
};

import dayjs from "dayjs";
import api from "@/api/mission";
import { MISSION_STATUS } from "@/utils/constants";

export default {
  namespaced: true,
  state: {
    assignedMissions: [],
    openedMissions: [],
    closedMissions: [],
    missions: [
      {
        id: 1,
        name: "체스 미션",
        startDateTime: "2020-09-02",
        dueDateTime: "2021-09-02",
        status: "ASSIGNED",
      },
      {
        id: 2,
        name: "블랙잭 미션",
        startDateTime: "2020-09-02",
        dueDateTime: "2021-09-02",
        status: "ASSIGNABLE",
      },
      {
        id: 3,
        name: "자동차 미션",
        startDateTime: "2020-09-02",
        dueDateTime: "2021-09-02",
        status: "NO_VACANCY",
      },
      {
        id: 1,
        name: "체스 미션",
        startDateTime: "2020-09-02",
        dueDateTime: "2021-09-02",
        status: "ASSIGNED",
      },
      {
        id: 2,
        name: "블랙잭 미션",
        startDateTime: "2020-09-02",
        dueDateTime: "2021-09-02",
        status: "ASSIGNABLE",
      },
      {
        id: 3,
        name: "자동차 미션",
        startDateTime: "2020-09-02",
        dueDateTime: "2021-09-02",
        status: "NO_VACANCY",
      },
      {
        id: 1,
        name: "체스 미션",
        startDateTime: "2020-09-02",
        dueDateTime: "2021-09-02",
        status: "ASSIGNED",
      },
      {
        id: 2,
        name: "블랙잭 미션",
        startDateTime: "2020-09-02",
        dueDateTime: "2021-09-02",
        status: "ASSIGNABLE",
      },
      {
        id: 3,
        name: "자동차 미션",
        startDateTime: "2020-09-02",
        dueDateTime: "2021-09-02",
        status: "NO_VACANCY",
      },
      {
        id: 4,
        name: "로또 미션",
        startDateTime: "2020-09-02",
        dueDateTime: "2019-09-02",
        status: "CLOSED",
      },
    ],
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

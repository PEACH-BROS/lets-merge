import dayjs from "dayjs";
import api from "@/api/mission";

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
    SET_MISSIONS(state) {
      state.missions = api.loadMissions();
      this.SET_ASSIGNED_MISSIONS(state);
      this.SET_OPENED_MISSIONS(state);
      this.SET_CLOSED_MISSIONS(state);
    },
    SET_ASSIGNED_MISSIONS(state) {
      state.assignedMissions = state.missions.filter(
        (mission) => mission.status === "ASSIGNED",
      );
    },
    SET_OPENED_MISSIONS(state) {
      state.openedMissions = state.missions
        .filter((mission) => mission.status !== "ASSIGNED")
        .filter((mission) => dayjs(mission.dueDateTime).isAfter(dayjs()));
    },
    SET_CLOSED_MISSIONS(state) {
      state.closedMissions = state.missions.filter((mission) =>
        dayjs(mission.dueDateTime).isBefore(dayjs()),
      );
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

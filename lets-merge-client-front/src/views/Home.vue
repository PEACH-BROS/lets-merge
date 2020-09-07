<template>
  <div class="container">
    <div class="font-weight-bold text-h4 align-self-start">나의 미션</div>
    <MissionItem
      v-for="mission in assignedMissions"
      :key="mission.id"
      :mission="mission"
      class="item"
    />

    <div class="font-weight-bold text-h4 align-self-start mt-16">
      모집 중인 미션
    </div>
    <MissionItem
      v-for="mission in openedMissions"
      :key="mission.id"
      :mission="mission"
      class="item"
    />

    <div class="font-weight-bold text-h4 align-self-start mt-16">
      종료된 미션
    </div>
    <MissionItem
      v-for="mission in closedMissions"
      :key="mission.id"
      :mission="mission"
      class="item"
    />
  </div>
</template>

<script>
import MissionItem from "@/views/MissionItem";

export default {
  name: "Home",
  components: {
    MissionItem,
  },
  async created() {
    await this.$store.dispatch("missions/setMissions");
  },
  computed: {
    assignedMissions() {
      return this.$store.getters["missions/getAssignedMissions"];
    },
    openedMissions() {
      return this.$store.getters["missions/getOpenedMissions"];
    },
    closedMissions() {
      return this.$store.getters["missions/getClosedMissions"];
    },
  },
};
</script>

<style>
.container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  flex-wrap: nowrap;
  align-items: center;
  align-self: center;
  width: 40%;
}

.container * {
  margin: 0;
}

.item {
  margin-top: 20px;
  padding: 10px;
  padding-bottom: 90px;
  overflow-y: hidden;
  min-width: 330px;
  box-shadow: 0 0 30px silver;
  border-radius: 20px;
}
</style>

import create from "zustand";
import mock_procceess from "../mock/mock_data.json";
import axios from "axios";

type ProcessStoreState = {
  allProcesses: any[];
  topLevelProcesses: any[];
  coreProcesses: any[];
  managementProcess: any[];
  supportProcess: any[];
  getTopLevelProcesses: Function;
  getCoreProcesses: Function;
  getManagementProcesses: Function;
  getSupportProcesses: Function;
};

export const useProcessStore = create<ProcessStoreState>((set, get) => ({
  allProcesses: mock_procceess,
  topLevelProcesses: [],
  coreProcesses: [],
  managementProcess: [],
  supportProcess: [],
  getTopLevelProcesses: () => {
    axios.get("/overviews").then((response) => {
      console.log(response.data);
      const filteredProcesses = response.data;
      // const filteredProcesses = response.data.filter(
      //   (e) => e.parentProcess === undefined
      // );
      filteredProcesses.sort((a: any, b: any) =>
        a.energySumYear < b.energySumYear ? 1 : -1
      );

      set({ allProcesses: filteredProcesses });
      set({ topLevelProcesses: filteredProcesses });

      get().getCoreProcesses();
      get().getManagementProcesses();
      get().getSupportProcesses();
    });
  },

  getCoreProcesses: () => {
    const filteredProcesses = get().topLevelProcesses.filter(
      (e) => e.processType === "CORE"
    );

    set({ coreProcesses: filteredProcesses });
  },
  getManagementProcesses: () => {
    const filteredProcesses = get().topLevelProcesses.filter(
      (e) => e.processType === "MANAGEMENT"
    );

    set({ managementProcess: filteredProcesses });
  },
  getSupportProcesses: () => {
    const filteredProcesses = get().topLevelProcesses.filter(
      (e) => e.processType === "Supportprozess"
    );

    set({ supportProcess: filteredProcesses });
  },
}));

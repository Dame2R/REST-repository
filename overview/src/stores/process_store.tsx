import create from "zustand";
import mock_procceess from "../mock/mock_data.json";

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
    const filteredProcesses = get().allProcesses.filter(
      (e) => e.parentProcess === undefined
    );
    filteredProcesses.sort((a, b) =>
      a.energySumYear < b.energySumYear ? 1 : -1
    );

    set({ topLevelProcesses: filteredProcesses });

    get().getCoreProcesses();
    get().getManagementProcesses();
    get().getSupportProcesses();
  },
  getCoreProcesses: () => {
    const filteredProcesses = get().topLevelProcesses.filter(
      (e) => e.processType === "core"
    );

    set({ coreProcesses: filteredProcesses });
  },
  getManagementProcesses: () => {
    const filteredProcesses = get().topLevelProcesses.filter(
      (e) => e.processType === "management"
    );

    set({ managementProcess: filteredProcesses });
  },
  getSupportProcesses: () => {
    const filteredProcesses = get().topLevelProcesses.filter(
      (e) => e.processType === "support"
    );

    set({ supportProcess: filteredProcesses });
  },
}));

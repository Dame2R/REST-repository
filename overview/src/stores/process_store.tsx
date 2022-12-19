import create from "zustand";
import mockProcesses from "../mock/mock_data.json";
import axios from "axios";
import { Process, ProcessId } from "../types/Process";

type ProcessStoreState = {
  allProcesses: Process[];
  topLevelProcesses: Process[];
  coreProcesses: Process[];
  managementProcesses: Process[];
  supportProcesses: Process[];
  fetchProcesses: () => void;
  getProcess: (id: ProcessId) => Process | undefined;
  getParentProcess: (id: ProcessId) => Process | undefined;
  getChildProcesses: (id: ProcessId, type: Process["processType"]) => Process[];
};

export const useProcessStore = create<ProcessStoreState>((set, get) => ({
  allProcesses: [],
  topLevelProcesses: [],
  coreProcesses: [],
  managementProcesses: [],
  supportProcesses: [],
  fetchProcesses: async () => {
    const processes = axios
      .get<Process[]>("/overviews")
      .then((response) => {
        console.log(response.data);
        return response.data;
      })
      .catch((reason) => {
        console.log("Failed to get processes", reason);
        return mockProcesses as Process[];
      })
      .then((processes) => {
        return processes.sort((a: any, b: any) =>
          a.energySumYear < b.energySumYear ? 1 : -1
        );
      });

    set({ allProcesses: await processes });
    set({
      topLevelProcesses: (await processes).filter(
        (p) => p.childProcess === undefined
      ),
    });
    set({ coreProcesses: (await processes).filter((p) => p.processType === "core") });
    set({
      managementProcesses: (await processes).filter(
        (p) => p.processType === "management"
      ),
    });
    set({
      supportProcesses: (await processes).filter((p) => p.processType === "support"),
    });
  },
  getProcess: (id: ProcessId): Process | undefined => {
    return get().allProcesses.find((p) => p.id === id);
  },
  getParentProcess: (id: ProcessId): Process | undefined => {
    const process = get().getProcess(id);
    return process?.childProcess ? get().getProcess(process.childProcess) : undefined;
  },
  getChildProcesses: (id: ProcessId, type: Process["processType"]): Process[] => {
    const allProcesses = get().allProcesses;
    return allProcesses.filter((p) => p.childProcess === id && p.processType === type);
  },
}));

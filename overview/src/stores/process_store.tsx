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
  getChildProcesses: (id: ProcessId, type: Process['type']) => Process[];
};

export const useProcessStore = create<ProcessStoreState>((set, get) => ({
  allProcesses: [],
  topLevelProcesses: [],
  coreProcesses: [],
  managementProcesses: [],
  supportProcesses: [],
  fetchProcesses: async () => {
    const processes = axios.get<Process[]>("/overviews")
      .then((response) => {
        return response.data;
      })
      .catch((reason) => {
        console.log('Failed to get processes', reason)
        return mockProcesses as Process[];
      })
      .then((processes) => {
        return processes.sort((a: any, b: any) =>
          a.energySumYear < b.energySumYear ? 1 : -1
        );
      })


    set({ allProcesses: await processes });
    set({ topLevelProcesses: (await processes).filter(p => p.parent === undefined) });
    set({ coreProcesses: (await processes).filter(p => p.type === 'core') });
    set({ managementProcesses: (await processes).filter(p => p.type === 'management') });
    set({ supportProcesses: (await processes).filter(p => p.type === 'support') });
  },
  getProcess: (id: ProcessId): Process | undefined => {
    return get().allProcesses.find(p => p.id === id);
  },
  getParentProcess: (id: ProcessId): Process | undefined => {
    const process = get().getProcess(id);
    return process?.parent ? get().getProcess(process.parent) : undefined;
  },
  getChildProcesses: (id: ProcessId, type: Process['type']): Process[] => {
    const allProcesses = get().allProcesses;
    return allProcesses.filter(p =>  p.parent === id && p.type === type)
  },
}));

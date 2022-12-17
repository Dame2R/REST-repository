export type ProcessId = string;

export type Process = {
  id: ProcessId;
  name: string;
  type: 'management' | 'core' | 'support';
  description: string;
  energySumYear: number;
  parent?: ProcessId;
  predecessors?: [ProcessId];
  successors?: [ProcessId];
} 
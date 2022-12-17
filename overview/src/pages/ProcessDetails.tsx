import { Stack } from "@mui/material";
import NavBar from "../components/Navbar";
import { useProcessStore } from "../stores/process_store";
import ProcessLandscape from "../components/ProcessLandscape";
import { useParams } from "react-router-dom";

export default function ProcessDetails() {
  const { id } = useParams();

  const getProcess = useProcessStore((state) => state.getProcess);
  const getParentProcess = useProcessStore((state) => state.getParentProcess);
  const getChildProcesses = useProcessStore((state) => state.getChildProcesses);

  const process = id ? getProcess(id) : undefined;
  const name = process?.name || 'Unbekannt';

  const parentProcess = id ? getParentProcess(id) : undefined;
  const coreProcesses = id ? getChildProcesses(id, 'core') : [];
  const managementProcesses = id ? getChildProcesses(id, 'management') : [];
  const supportProcesses = id ? getChildProcesses(id, 'support') : [];

  return (
    <div>
      <Stack>
        <NavBar
          title={"Prozess: " + name}
        />
        <ProcessLandscape
          parentProcess={parentProcess}
          coreProcesses={coreProcesses}
          managementProcesses={managementProcesses}
          supportProcesses={supportProcesses}
        />
      </Stack>
    </div>
  );
}

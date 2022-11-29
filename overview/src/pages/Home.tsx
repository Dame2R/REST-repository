import { Stack } from "@mui/material";
import { MuiSnackbar } from "../components/MuiSnackbar";
import NavBar from "../components/Navbar";
import ProcessLandscape from "../components/ProcessLandscape";
import { useProcessStore } from "../stores/process_store";

export default function Home() {
  const topLevelProcesses: any = useProcessStore(
    (state) => state.topLevelProcesses
  );
  const getTopLevelProcesses = useProcessStore(
    (state) => state.getTopLevelProcesses
  );
  const coreProcesses = useProcessStore((state) => state.coreProcesses);
  const managementProcess = useProcessStore((state) => state.managementProcess);
  const supportProcess = useProcessStore((state) => state.supportProcess);

  if (topLevelProcesses.length == 0) {
    getTopLevelProcesses();
  }

  return (
    <Stack>
      <NavBar />
      <ProcessLandscape
        coreProcesses={coreProcesses}
        managementProcesses={managementProcess}
        supportProcesses={supportProcess}
      />
      <MuiSnackbar />
    </Stack>
  );
}

import { Stack } from "@mui/material";
import { MuiSnackbar } from "../components/MuiSnackbar";
import NavBar from "../components/Navbar";
import ProcessLandscape from "../components/ProcessLandscape";
import { useProcessStore } from "../stores/process_store";

export default function Home() {
  const topLevelProcesses = useProcessStore(
    (state) => state.topLevelProcesses
  );
  const fetchProcesses = useProcessStore((state) => state.fetchProcesses);
  const coreProcesses = useProcessStore((state) => state.coreProcesses);
  const managementProcess = useProcessStore((state) => state.managementProcesses);
  const supportProcess = useProcessStore((state) => state.supportProcesses);

  if (topLevelProcesses.length === 0) {
    fetchProcesses();
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

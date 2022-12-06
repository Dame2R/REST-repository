import { Stack } from "@mui/material";
import NavBar from "../components/Navbar";
import { useProcessStore } from "../stores/process_store";
import ProcessLandscape from "../components/ProcessLandscape";
import { useParams } from "react-router-dom";

export default function ProcessEnergySort() {
  const { energyLevel } = useParams();

  const allProcesses: any = useProcessStore((state) => state.allProcesses);

  var filteredProcesses: any = [];

  if (energyLevel === "low") {
    filteredProcesses = allProcesses.filter((obj: any) => {
      return obj.energySumYear <= 30;
    });
  }
  if (energyLevel === "medium") {
    filteredProcesses = allProcesses.filter((obj: any) => {
      return obj.energySumYear > 30 && obj.energySumYear <= 60;
    });
  }
  if (energyLevel === "high") {
    filteredProcesses = allProcesses.filter((obj: any) => {
      return obj.energySumYear > 60;
    });
  }

  return (
    <div>
      <Stack>
        <NavBar />
        <ProcessLandscape
          coreProcesses={filteredProcesses.filter(
            (p: any) => p.processType === "CORE"
          )}
          managementProcesses={filteredProcesses.filter(
            (p: any) => p.processType === "MANAGEMENT"
          )}
          supportProcesses={filteredProcesses.filter(
            (p: any) => p.processType === "Supportprozess"
          )}
        />
      </Stack>
    </div>
  );
}

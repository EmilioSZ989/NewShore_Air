import { FlightModel } from "./flight-model";

export interface JourneyModel {
    id: number;
    flights: FlightModel[];
    origin: string;
    destination: string;
    price: number;
  }
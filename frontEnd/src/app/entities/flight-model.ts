import { TransportModel } from "./transport-model";

export interface FlightModel {
    id: number;
    transport: TransportModel;
    origin: string;
    destination: string;
    price: number;
  }
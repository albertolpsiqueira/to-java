import { Module } from "@nestjs/common";
import { MongooseModule } from "@nestjs/mongoose";
import { UserModule } from "./user/user.module";

const mongoConnectionString = process.env.MONGO_URL;
if (!mongoConnectionString) {
  throw new Error("Mongo environment variable is not set");
}

@Module({
  imports: [MongooseModule.forRoot(mongoConnectionString), UserModule],
})
export class AppModule {}

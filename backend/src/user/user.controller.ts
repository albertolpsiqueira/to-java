import {
  Controller,
  Get,
  Post,
  Put,
  Delete,
  Body,
  Param,
} from "@nestjs/common";
import { UserService } from "./user.service";
import { User } from "./user.model";

@Controller("users")
export class UserController {
  constructor(private readonly userService: UserService) {}

  @Post()
  async create(@Body() data: Partial<User>) {
    return this.userService.create(data);
  }

  @Get()
  async findAll() {
    return this.userService.findAll();
  }

  @Get(":id")
  async findOne(@Param("id") id: string) {
    return this.userService.findOne(id);
  }

  @Put(":id")
  async update(@Param("id") id: string, @Body() data: Partial<User>) {
    return this.userService.update(id, data);
  }

  @Delete(":id")
  async remove(@Param("id") id: string) {
    await this.userService.remove(id);
    return { deleted: true };
  }
}

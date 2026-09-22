using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace FlowFund.Api.Migrations
{
    /// <inheritdoc />
    public partial class AddContributionAndPayoutFields : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<byte>(
                name: "RotationStrategy",
                table: "StokvelGroups",
                type: "tinyint",
                nullable: false,
                defaultValue: (byte)0);

            migrationBuilder.AddColumn<decimal>(
                name: "Amount",
                table: "PayoutSchedules",
                type: "decimal(18,2)",
                precision: 18,
                scale: 2,
                nullable: false,
                defaultValue: 0m);

            migrationBuilder.AddColumn<DateTime>(
                name: "CreatedAt",
                table: "PayoutSchedules",
                type: "datetime2",
                nullable: false,
                defaultValue: new DateTime(1, 1, 1, 0, 0, 0, 0, DateTimeKind.Unspecified));

            migrationBuilder.AddColumn<int>(
                name: "RotationPosition",
                table: "PayoutSchedules",
                type: "int",
                nullable: false,
                defaultValue: 0);

            migrationBuilder.AddColumn<int>(
                name: "ConfirmedByUserId",
                table: "Contributions",
                type: "int",
                nullable: true);

            migrationBuilder.AddColumn<DateTime>(
                name: "CreatedAt",
                table: "Contributions",
                type: "datetime2",
                nullable: false,
                defaultValue: new DateTime(1, 1, 1, 0, 0, 0, 0, DateTimeKind.Unspecified));

            migrationBuilder.AddColumn<DateTime>(
                name: "PaidDate",
                table: "Contributions",
                type: "datetime2",
                nullable: true);

            migrationBuilder.AddColumn<string>(
                name: "PaymentReference",
                table: "Contributions",
                type: "nvarchar(max)",
                nullable: true);
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "RotationStrategy",
                table: "StokvelGroups");

            migrationBuilder.DropColumn(
                name: "Amount",
                table: "PayoutSchedules");

            migrationBuilder.DropColumn(
                name: "CreatedAt",
                table: "PayoutSchedules");

            migrationBuilder.DropColumn(
                name: "RotationPosition",
                table: "PayoutSchedules");

            migrationBuilder.DropColumn(
                name: "ConfirmedByUserId",
                table: "Contributions");

            migrationBuilder.DropColumn(
                name: "CreatedAt",
                table: "Contributions");

            migrationBuilder.DropColumn(
                name: "PaidDate",
                table: "Contributions");

            migrationBuilder.DropColumn(
                name: "PaymentReference",
                table: "Contributions");
        }
    }
}

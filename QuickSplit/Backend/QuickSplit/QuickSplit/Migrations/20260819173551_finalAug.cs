using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace QuickSplit.Migrations
{
    /// <inheritdoc />
    public partial class finalAug : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<int>(
                name: "paidBy",
                table: "Transaction",
                type: "int",
                nullable: false,
                defaultValue: 0);
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "paidBy",
                table: "Transaction");
        }
    }
}

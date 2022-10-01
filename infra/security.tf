resource "ncloud_access_control_group_rule" "acg_rules" {
  access_control_group_no = ncloud_vpc.vpc.default_access_control_group_no

  inbound {
    protocol    = "TCP"
    ip_block    = "0.0.0.0/0"
    port_range  = "80"
    description = "added by terraform"
  }
}

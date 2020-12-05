package io.h8.cfg

import java.nio.file.Path

class Factory(provider: CfgProvider, val NodeParserString: NodeParser[String] = NodeParserPlainString):
  given Factory = this

  def parse(content: String): CfgNode = provider.parse(content)

  def apply(): CfgNode = provider()

  def apply(paths: Iterable[Path]): CfgNode = if (paths.isEmpty) apply() else provider(paths)

  def apply(paths: Path*): CfgNode = apply(paths)

  implicit final class CfgStringContext(sc: StringContext):
    def cfg(args: Any*): CfgNode = parse(sc.s(args: _*).stripMargin)
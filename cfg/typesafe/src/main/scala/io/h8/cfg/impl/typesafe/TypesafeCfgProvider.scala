package io.h8.cfg.impl.typesafe

import java.nio.file.Path
import com.typesafe.config.ConfigFactory
import io.h8.cfg.{CfgNode, CfgProvider, Factory}

object TypesafeCfgProvider extends CfgProvider:
  override def parse(content: String)(using factory: Factory): CfgNode =
    new TypesafeCfgNode(ConfigFactory.parseString(content))

  override def apply()(using factory: Factory): CfgNode = new TypesafeCfgNode(ConfigFactory.load)

  override def apply(paths: Iterable[Path])(using factory: Factory): CfgNode = new TypesafeCfgNode(
    (paths.iterator map { path =>
      ConfigFactory.parseFile(path.toFile)
    } reduce { (prev, next) => next withFallback prev }).resolve)
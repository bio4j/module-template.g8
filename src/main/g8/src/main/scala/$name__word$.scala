package ohnosequences.bio4j.bundles

import shapeless._
import shapeless.ops.hlist._
import ohnosequences.typesets._
import ohnosequences.statika._
import ohnosequences.statika.aws._
import ohnosequences.statika.ami._
import ohnosequences.bio4j.statika._
import ohnosequences.awstools.s3._
import ohnosequences.awstools.regions._
import com.ohnosequences.bio4j.titan.programs._
import java.io._

/* This bundle is important, it doesn't really import anything, but initializes Bio4j */
case object InitialBio4j extends Bundle() with AnyBio4jInstanceBundle {
  val dbLocation: File = new File("/media/ephemeral0/bio4jtitandb")

  override def install[D <: AnyDistribution](d: D): InstallResults = {
    if (!dbLocation.exists) dbLocation.mkdirs
    InitBio4jTitan.main(Array(dbLocation.getAbsolutePath))
    success("Initialized Bio4j DB in " + dbLocation)
  }
}

case object $name;format="word"$RawData 
  extends RawDataBundle(???)

case object $name;format="word"$API extends APIBundle(){}

case class $name;format="word"$Program(
  ???
) extends ImporterProgram(new Import$name;format="word"$Titan(), Seq(
  ???
))

case object $name;format="word"$ImportedData extends ImportedDataBundle(
    rawData = $name;format="word"$RawData :~: ∅,
    initDB = InitialBio4j,
    importDeps = ∅
  ) {
  override def install[D <: AnyDistribution](d: D): InstallResults = {
    $name;format="word"$Program(
      ???
    ).execute ->-
    success("Data " + name + " is imported to" + dbLocation)
  }
}

case object $name;format="word"$Module extends ModuleBundle($name;format="word"$API, $name;format="word"$ImportedData)

case object $name;format="word"$Metadata extends generated.metadata.$name;format="Camel"$Module()

case object $name;format="word"$Release extends ReleaseBundle(
  ObjectAddress("bio4j.releases", 
                "$name;format="lower,snake"$/v" + $name;format="word"$Metadata.version.stripSuffix("-SNAPSHOT")), 
  $name;format="word"$Module
)

case object $name;format="word"$Distribution extends DistributionBundle(
  $name;format="word"$Release,
  destPrefix = new File("/media/ephemeral0/")
)


package io.github.brunogabriel.coretest.robots

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * Created by bruno on 27/02/20
 */
class RobotsRule<T : Robots>(
    private val robot: T
) : TestRule {
    override fun apply(base: Statement, description: Description?): Statement {
        return object : Statement() {
            override fun evaluate() {
                base.evaluate()
            }
        }
    }

    fun setup() = robot.setup()
    operator fun invoke(func: T.() -> Unit) = createRobots(func, robot)
}

private fun <T> createRobots(func: T.() -> Unit, robot: T) = robot.apply { func() }
